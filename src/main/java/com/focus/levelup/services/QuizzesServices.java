package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Quizzes;

public interface QuizzesServices extends CrudRepository<Quizzes, Integer> {

	// Get all quizzes by status
	@Query("SELECT q FROM Quizzes q WHERE q.status = :status")
	List<Quizzes> findAllByStatus(@Param("status") int status);

	// Filtering quizzes by administrator
	@Query("SELECT q FROM Quizzes q WHERE q.user.idUser = :adminId")
	List<Quizzes> findAllByAdminId(@Param("adminId") int adminId);

	// Filtering quizzes by administrator and programming language id
	@Query("SELECT q FROM Quizzes q WHERE q.user.idUser = :adminId AND q.programmingLanguage.idLanguage = :languageId")
	List<Quizzes> findAllByAdminAndLanguageId(@Param("adminId") int adminId, @Param("languageId") int languageId);

	// Filtering quizzes by administrator and programming language name
	@Query("SELECT q FROM Quizzes q WHERE q.user.idUser = :adminId AND q.programmingLanguage.language LIKE CONCAT('%',:language,'%')")
	List<Quizzes> findAllByAdminAndLanguageName(@Param("adminId") int adminId, @Param("language") String language);

	// Filtering quizzes by administrator and level id
	@Query("SELECT q FROM Quizzes q WHERE q.user.idUser = :adminId AND q.quizLevel.idLevel = :levelId")
	List<Quizzes> findAllByAdminAndLevelId(@Param("adminId") int adminId, @Param("levelId") int levelId);

	// Filtering quizzes by administrator and level name
	@Query("SELECT q FROM Quizzes q WHERE q.user.idUser = :adminId AND q.quizLevel.level LIKE CONCAT('%',:level,'%')")
	List<Quizzes> findAllByAdminAndLevelName(@Param("adminId") int adminId, @Param("level") String level);

	// Filtering quizzes by administrator, programming language and level
	@Query("SELECT q FROM Quizzes q WHERE q.user.idUser = :adminId AND q.programmingLanguage.idLanguage = :languageId AND q.quizLevel.idLevel = :levelId")
	List<Quizzes> findAllByAdminLanguageIdAndLevelId(@Param("adminId") int adminId, @Param("languageId") int languageId, @Param("levelId") int levelId);

	// Filtering quizzes by administrator, programming language or level name
	@Query("SELECT q FROM Quizzes q WHERE q.user.idUser = :adminId AND q.programmingLanguage.language LIKE CONCAT('%',:languageOrLevel,'%') AND q.quizLevel.level LIKE CONCAT('%',:languageOrLevel,'%')")
	List<Quizzes> findAllByAdminLanguageOrLevelName(@Param("adminId") int adminId, @Param("languageOrLevel") String languageOrLevel);
}
