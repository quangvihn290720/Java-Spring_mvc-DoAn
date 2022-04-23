package com.khoaluantotnghiep.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "baseControllerOfAdmin")
public class BaseController {
	public ModelAndView _mvShare = new ModelAndView();

	public String saveFile(MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = System.getProperty("catalina.home");
				// tao folder luu file
				File dir = new File(rootPath + File.separator + "images");
				if (!dir.exists()) {
					dir.mkdirs();
				}
				// tao file tren server
				String name = String.valueOf(new Date().getTime() + ".jpg");
				File serverFile = new File(dir.getAbsoluteFile() + File.separator + name);
				//
				System.out.println("==============Path of img on server" + serverFile.getPath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				return name;
			} catch (IOException e) {
				System.out.println("==============Error upload file" + e.getMessage());
			}
		} else {
			System.out.println("==============File not Exits");
		}
		return null;
	}
}
