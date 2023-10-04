package com.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.domain.FileDTO;

import net.coobird.thumbnailator.Thumbnailator;

@Controller
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
	}
	
	@PostMapping("/uploadAction")
	public void upload(MultipartFile[] uploadFile, Model model) {
		System.out.println("[uploadAction]");
		
		String uploadFolder = "d:\\upload";
		
		for(MultipartFile file : uploadFile) {
			System.out.println("----------------");
			System.out.println("파일명: " + file.getOriginalFilename());
			System.out.println("크기 : " + file.getSize());
			
			File saveFile = new File(uploadFolder, file.getOriginalFilename());
			
			try {
				file.transferTo(saveFile);		// 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
	}
	
	@PostMapping(value="/uploadAjax", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<FileDTO>> upload(MultipartFile[] uploadFile){
		List<FileDTO> list = new ArrayList<>();
		String uploadFolder = "d:\\upload";
		String uploadFolderPath = getFolder();
		
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile file : uploadFile) {
			FileDTO dto = new FileDTO();
			
			String uploadFileName = file.getOriginalFilename();
			dto.setFileName(uploadFileName);
			UUID uuid = UUID.randomUUID();
			dto.setUuid(uuid.toString());
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				file.transferTo(saveFile);
				dto.setUploadPath(uploadFolderPath);
				
				if(checkImageType(saveFile)) {
					dto.setImage(true);
					
					FileOutputStream thrumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(file.getInputStream(), thrumbnail, 100, 100);
					thrumbnail.close();
				}
				
				list.add(dto);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 폴더생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	// 파일이 이미지 인지 검사(섬네일 만들거임)
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());	// image/jpg, image/png....
			
			return contentType.startsWith("image");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		File file = new File("d:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders(); // 이미지 파일이므로 표시를 위한 작업
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(String fileName){
		
		System.out.println("다운받는 파일명: " + fileName);
		
		FileSystemResource resource = new FileSystemResource("d:\\upload\\" + fileName);
		
		System.out.println("리소스 : " + resource);
		
		String resourceName = resource.getFilename();
		String oriName = resourceName.substring(resourceName.indexOf("_") + 1);
		
		HttpHeaders header = new HttpHeaders();
		
		try {
			// 다운받을 파일명 설정
			header.add("Content-Disposition", 
						"attachment; filename=" + new String(oriName.getBytes("utf-8"), "iso-8859-1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(resource, header, HttpStatus.OK);
		
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type) {

		File file = null;
		
		try {
			// URLDecoder.decode : URIEncode로 저장된 변수이므로...
			file = new File("d:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
			file.delete();	// 섬네일 또는 일반파일 삭제
			
			// 이미지는 원본파일도 지워야함(이미지는 원본 + 섬네일이 서버에 저장되어있음)
			if(type.equals("image")) {
				String oriFileName = file.getAbsolutePath().replace("s_", "");
				file = new File(oriFileName);
				file.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("DELETE OK", HttpStatus.OK);
		
	}
	
//	@PostMapping("/uploadAjax")
//	public void upload(MultipartFile[] uploadFile) {
//		String path="d:\\upload";		// 서버주소
//		
//		File uploadPath = new File(path, getFolder());
//		
//		if(uploadPath.exists() == false) {
//			uploadPath.mkdirs();
//		}
//		
//		for(MultipartFile file : uploadFile) {
//			System.out.println("----------------");
//			System.out.println("파일명: " + file.getOriginalFilename());
//			
//			// 중복파일명 UUID
//			UUID uuid = UUID.randomUUID();
//			String uploadFileName = uuid.toString() + "_" + file.getOriginalFilename();
//			
//			File saveFile = new File(uploadPath, uploadFileName);
//			
//			try {
//				file.transferTo(saveFile);
//				
//				// 섬네일 만들기
//				if(checkImageType(saveFile)) {
//					FileOutputStream thrumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
//					Thumbnailator.createThumbnail(file.getInputStream(), thrumbnail, 100, 100);
//					thrumbnail.close();
//				}
//				
//				// return client data
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
}
