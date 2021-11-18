package com.lexisnexis.risk.bot.service.noname;

import com.lexisnexis.risk.bot.base.BaseServiceImpl;
import com.lexisnexis.risk.bot.dao.KudoRepository;
import com.lexisnexis.risk.bot.model.Kudo;
import com.lexisnexis.risk.bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class KudoServiceImpl extends BaseServiceImpl<Kudo, Long> implements KudoService{

    private final KudoRepository kudoRepository;

    public KudoServiceImpl(KudoRepository kudoRepository) {
        this.kudoRepository = kudoRepository;
    }

    @Override
    protected JpaRepository<Kudo, Long> getRepository() {
        return this.kudoRepository;
    }
}
