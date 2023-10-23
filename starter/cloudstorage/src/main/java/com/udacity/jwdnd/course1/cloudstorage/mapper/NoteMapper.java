package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM  NOTES WHERE noteId = #{noteId}")
    Note getNote(Integer noteId);

    @Select("SELECT * FROM  NOTES WHERE userid = #{userid}")
    Note[] getNotesByUserId(Integer userid);

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Update("UPDATE NOTES  SET notetitle = #{noteTitle}, notedescription = #{noteDescription}, userid = #{userId} WHERE noteId= #{noteId}")
    void updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE NoteId = #{id}")
    void delete(Integer id);
}
