package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.bean.MyFile;
import com.example.demo.mapper.FileMapper;
import com.example.demo.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, MyFile> implements FileService {
}
