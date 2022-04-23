package com.khoaluantotnghiep.ultis;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoadImage
 */
@WebServlet(urlPatterns = "/images/*")
public class LoadImage extends HttpServlet {

	private String imagePath;

	@Override
	public void init() throws ServletException {
		imagePath = System.getProperty("catalina.home")+File.separator+"images";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestImage = request.getPathInfo();
		if (requestImage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		File image = new File(imagePath, URLDecoder.decode(requestImage, "UTF-8"));
		// Check if file actually exists in file system.
		if (!image.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		String contentType = getServletContext().getMimeType(image.getName());
		// check if file is actually an image
		if (contentType == null || !contentType.startsWith("image")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));
		// write img content to reponse
		Files.copy(image.toPath(), response.getOutputStream());

	}

}
