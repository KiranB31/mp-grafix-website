package com.grafix.website.repository;

import com.grafix.website.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessInfo, Long> {
}
