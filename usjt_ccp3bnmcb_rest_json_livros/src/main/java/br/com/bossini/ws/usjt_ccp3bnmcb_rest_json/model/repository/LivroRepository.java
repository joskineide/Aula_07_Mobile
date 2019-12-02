package br.com.bossini.ws.usjt_ccp3bnmcb_rest_json.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bossini.ws.usjt_ccp3bnmcb_rest_json.model.beans.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
