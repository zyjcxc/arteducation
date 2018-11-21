package com.edu.admin.server.service;

import com.edu.admin.server.model.Mail;

import java.util.List;

public interface MailService {

	void save(Mail mail, List<String> toUser);
}
