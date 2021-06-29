package com.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.dto.code;

@Component
public class EmailUtils {

	@Autowired
	JavaMailSender mailSender;
	
	public static List<MimeMessage> queue = new ArrayList<MimeMessage>();
	public static List<code> codes = new ArrayList<code>();
	
	public void addcode(code code) {
		codes.add(code);
	}
	
	
	public void putMimeMessage(String to,String subject,String  content) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content,true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		queue.add(message);
	}
	
	public code findcode(String code) {
		code c = null;
		for(code co:codes) {
			if(code.equals(co.getCode())) {
				c = co;
			codes.remove(co);
			return c;
			}
		}
		return c;
	}
	
	@Scheduled(fixedRate = 60000)
	public void clearcodes() {
		if(!queue.isEmpty()) {
			codes = codes.stream().filter((code)->{return code.isTimeout();}).collect(Collectors.toList());
		}
	}
	
	@Scheduled(fixedRate = 2000)
	public void sendEmail() {
		if(!queue.isEmpty()) {
			mailSender.send(queue.remove(0));
		}
	}
	
}
