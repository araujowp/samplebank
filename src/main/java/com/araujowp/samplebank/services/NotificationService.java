package com.araujowp.samplebank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.araujowp.samplebank.domain.user.User;
import com.araujowp.samplebank.dtos.NotificationDTO;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String message) throws Exception {

		String email = user.getEmail();

		try {			
			NotificationDTO notificationRequest = new NotificationDTO(email, message);
			
			ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify" , notificationRequest, String.class);
			
			if(!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
				throw new Exception("Service de notificação esta indisponivel");
			}
		}catch (Exception e) {
			System.out.println("Não foi possivel notificar " + e.getMessage());
//			throw new Exception("Falha ao conectar no serviço de comunicação");
		}
	}
}
