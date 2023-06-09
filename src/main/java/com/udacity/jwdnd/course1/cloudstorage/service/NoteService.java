package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotesByUserId(Integer userId) {
        return noteMapper.getAllNotes(userId);
    }

    public int insertNote(Note note) {
        return noteMapper.insertNote(note);
    }

    public Note getInfoNote(Integer noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public int updatNote(Note note){
        return noteMapper.updateNote(note);
    }

    public int deleteNote(Integer nodeId){return noteMapper.deleteNote(nodeId);}

}
