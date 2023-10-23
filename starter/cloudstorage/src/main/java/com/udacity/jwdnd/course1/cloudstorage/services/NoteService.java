package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class NoteService {
    private NoteMapper noteMapper;

  // @AuthenticationPrincipal User currLoggedInUser

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(User currentLoggedIn, Note note) {
        return noteMapper.insert(new Note(note.getNoteTitle(), note.getNoteDescription(), currentLoggedIn.getUserId()));
    }

    public Note[] getNotesByUserName(@AuthenticationPrincipal User currentLoggedIn) {
        return noteMapper.getNotesByUserId(currentLoggedIn.getUserId());
    }
    public Note getNoteById(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.delete(noteId);
    }

    public void updateNote(Note note) {
         noteMapper.updateNote(note);
    }
}
