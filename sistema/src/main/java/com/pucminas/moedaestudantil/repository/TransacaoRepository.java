package com.pucminas.moedaestudantil.repository;

import com.pucminas.moedaestudantil.model.Transacao;
import com.pucminas.moedaestudantil.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByRemetenteOrDestinatario(Usuario remetente, Usuario destinatario);
}
