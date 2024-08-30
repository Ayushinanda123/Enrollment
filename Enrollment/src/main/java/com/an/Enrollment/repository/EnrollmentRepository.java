package com.an.Enrollment.repository;

import com.an.Enrollment.entity.Enrollment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    //Optional<Enrollment> findByEmpIdAndCourseId(Integer empId, Integer courseId);

//    void deleteByEmpIdAndCourseId(Integer empId,Integer courseId);

    Optional<Enrollment> findByEnrollId(String enrolId);

    void deleteByEnrollId(String enrollId);
}
