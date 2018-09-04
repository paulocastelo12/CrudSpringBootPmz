package com.crudpmz.repository;

import org.springframework.data.repository.CrudRepository;

import com.crudpmz.models.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, String> {
   Aluno findByCodigo(long codigo);
   
}
