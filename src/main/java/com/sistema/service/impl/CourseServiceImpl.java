package com.sistema.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sistema.entity.Course;
import com.sistema.repository.CourseJpaRepository;
import com.sistema.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService{

	private static final Log LOGGER = LogFactory.getLog(CourseServiceImpl.class);
	
	@Autowired
	@Qualifier("courseJpaRepository")
	private CourseJpaRepository courseJpaRepository;
		
	@Override
	public List<Course> listAllCourses() {
		LOGGER.info("CALL: listAllCourses() ");
		return courseJpaRepository.findAll();
	}

	@Override
	public Course addCourse(Course course) {
		LOGGER.info("CALL: addCourse() ");
		return courseJpaRepository.save(course);
	}

	@Override
	public int removeCourse(int id) {
		LOGGER.info("CALL: removeCourse() -- PARAM: " + id );
		courseJpaRepository.delete(id);
		return 0;
	}

	@Override
	public Course updateCourse(Course course) {
		LOGGER.info("CALL: updateCourse() " + course.toString() );
		return courseJpaRepository.save(course);
	}

}
