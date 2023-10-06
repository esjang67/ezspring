package com.board.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.board.domain.BoardAttachVO;
import com.board.mapper.BoardAttachMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	
	// 첨부파일이 서버에 올라갔는데 필요없는 파일은 삭제하도록함
	
	@Autowired
	BoardAttachMapper boardAttachMapper;
	
	private String getFolderYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);	// 어제날짜
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	
	// 폴더생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron = "* * 0 * * *" )	// 초 분 시 일 월 요일(0,7:일요일...)
	public void checkFiles() throws Exception {
		log.warn("----------------");
		
		//--------------------------------------------------------------------
		// db list 가져오기
		List<BoardAttachVO> fileList = boardAttachMapper.getList();
		
		List<Path> fileListPath = fileList.stream()
								.map(vo -> Paths.get("d:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName() ))  // 주소만듬
								.collect(Collectors.toList());		// 컬렉션 추가
		
		// 이미지이면 섬네일도 삭제리스트에 추가함
		fileList.stream().filter(vo -> vo.isFileType() == true)	// isFileType 이 true면 이미지파일
						.map(vo -> Paths.get("d:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName() ))
						.forEach(path -> fileListPath.add(path));
		
		//fileListPath.forEach(f-> log.warn(f));
		
		//--------------------------------------------------------------------
		// 서버파일과 비교해서 없는건 삭제 할것 
		File dir = Paths.get("d:\\upload", getFolderYesterday()).toFile();
		File[] removeFiles = dir.listFiles(file -> fileListPath.contains(file.toPath()) == false);
		
		log.warn("----------------  삭제할 파일");
		for(File f : removeFiles) {
			log.warn(f);
			f.delete();
		}
		
		
	}
	
	
	
	
}
