package com.pams.dao;

import org.springframework.stereotype.Component;

import com.blue.ssh.core.orm.hibernate.HibernateDao;
import com.pams.entity.FileAttachment;

@Component
public class FileAttachmentDao extends HibernateDao<FileAttachment, String>
{

}
