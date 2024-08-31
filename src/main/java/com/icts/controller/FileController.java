package com.icts.controller;

import com.alibaba.fastjson2.JSON;
import com.icts.model.File;
import com.icts.model.FileQueryRequest;
import com.icts.model.Msg;
import com.icts.model.Response;
import com.icts.model.request.MsgBatchQueryRequest;
import com.icts.repository.FileRepository;
import com.icts.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private EmailService emailService;

    public static final String FILE_PATH = "/root/register";

    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public Response<List<String>> query() {
        List<File> files = fileRepository.query();
        if (CollectionUtils.isEmpty(files)) {
            return Response.success(new ArrayList<>());
        }
        return Response.success(files.stream().map(File::getFileName).collect(Collectors.toList()));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public Response<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            log.error("file is empty");
            return Response.failed("file is empty");
        }

        try {
            // 创建存储上传文件的目录（如果不存在）
            Path uploadDir = Paths.get(FILE_PATH);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 获取原始文件名和后缀
            String originalFileName = file.getOriginalFilename();
            if (StringUtils.isBlank(originalFileName)) {
                log.error("file name is empty");
                return Response.failed("file is empty");

            }
            String fileSuffix = "";
            int dotIndex = originalFileName.lastIndexOf('.');
            if (dotIndex > 0) {
                fileSuffix = originalFileName.substring(dotIndex);
            }

            // 获取当前时间戳
            long timestamp = System.currentTimeMillis();

            // 生成新文件名
            String newFileName = originalFileName.substring(0, originalFileName.lastIndexOf('.')) + "_" + timestamp + fileSuffix;
            Path filePath = uploadDir.resolve(newFileName);

            // 保存文件
            file.transferTo(filePath.toFile());
            // 插入数据库
            fileRepository.insert(newFileName);
            // 异步发邮件
            emailService.sendEmailWithAttachment(newFileName, FILE_PATH);
            return Response.success(newFileName);
        } catch (IOException e) {
            log.error("upload file error", e);
            return Response.failed(e.getMessage());
        }
    }
}
