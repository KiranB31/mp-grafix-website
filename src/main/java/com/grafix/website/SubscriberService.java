package com.grafix.website;

import com.grafix.website.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    public List<Subscriber> getAllSubscribers() {
        return subscriberRepository.findAll();
    }

    public void addSubscriber(String email) {
        if (subscriberRepository.findByEmail(email).isEmpty()) {
            Subscriber subscriber = new Subscriber();
            subscriber.setEmail(email);
            subscriberRepository.save(subscriber);
        }
    }

    public void removeSubscriber(Long id) {
        subscriberRepository.deleteById(id);
    }
}
