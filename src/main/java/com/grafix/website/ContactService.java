package com.grafix.website;

import com.grafix.website.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<ContactMessage> getAllMessages() {
        return contactRepository.findAll();
    }

    public void saveMessage(ContactMessage message) {
        contactRepository.save(message);
    }

    public void deleteMessage(Long id) {
        contactRepository.deleteById(id);
    }
}
