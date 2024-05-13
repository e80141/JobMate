//package com.job.repository;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.job.dto.UserDto;
//import com.job.mapper.UserMapper;
//
//@Repository
//public interface UserRepository{
//	@Autowired
//    private SqlSessionFactory sqlSessionFactory;
//	 public void insertUser(UserDto userDto) {
//	        try (SqlSession session = sqlSessionFactory.openSession()) {
//	            UserMapper mapper = session.getMapper(UserMapper.class);
//	            mapper.insertUser(userDto);
//	        }
//	    }
//}