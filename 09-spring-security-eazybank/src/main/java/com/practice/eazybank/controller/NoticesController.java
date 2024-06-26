package com.practice.eazybank.controller;

import com.practice.eazybank.model.Notice;
import com.practice.eazybank.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getBalanceDetails(){

		List<Notice> notices = noticeRepository.findAllActiveNotices();

		if (notices != null){
			return ResponseEntity.ok()
					.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
					.body(notices);
		} else return null;
    }
}
