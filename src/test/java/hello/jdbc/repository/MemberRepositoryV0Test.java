package hello.jdbc.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV5", 10000);
        repository.save(member);

        Member findMember = repository.findById("memberV5");
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(member);
        
        repository.update("memberV5", 20000);
        Member updatedMember = repository.findById("memberV5");
        assertThat(updatedMember.getMoney()).isEqualTo(20000);
        
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
        		.isInstanceOf(NoSuchElementException.class);
    }
}
