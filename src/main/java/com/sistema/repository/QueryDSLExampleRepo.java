package com.sistema.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.sistema.entity.Course;
import com.sistema.entity.QCourse;

@Repository("queryDSLExampleRepo")
public class QueryDSLExampleRepo {

	private QCourse qCourse = QCourse.course;
	
	@PersistenceContext
	private EntityManager em;
	
	public Course  find(boolean exist){
		
		JPAQuery<Course> query = new JPAQuery<Course>(em);
		
		BooleanBuilder predicateBuilder = new BooleanBuilder();
		
		//El objeto que admite el WHERE es un Predicate.
		Predicate predicate1 = qCourse.description.contains("android");
		
		if (exist) {
			Predicate predicate2 = qCourse.id.eq(1);
			predicateBuilder.and(predicate2);
		} else {
			Predicate predicate3 = qCourse.id.eq(1);
			predicateBuilder.or(predicate3);
		}
		/*
		Course course = query.select(qCourse).from(qCourse).where(qCourse.id.eq(2)).fetchOne();
		Course course1 = (Course) query.select(qCourse.name,qCourse.description).from(qCourse).where(qCourse.id.eq(2)).fetchOne();
		List<Course> courses = (List<Course>) query.select(qCourse).from(qCourse).where(qCourse.hours.between(5, 25));
		*/
		return query.select(qCourse).from(qCourse).where(predicateBuilder).fetchOne();
	}
}
