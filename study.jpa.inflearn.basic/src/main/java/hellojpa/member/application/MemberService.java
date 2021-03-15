package hellojpa.member.application;

import hellojpa.member.domain.Member;
import hellojpa.member.dto.MemberResponse;

import javax.persistence.*;
import java.util.List;

import static hellojpa.member.application.MemberQuery.*;

public class MemberService {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public MemberService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("hello-shop");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    public void start() {
        this.transaction.begin();
    }

    public void end() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    public List<MemberResponse> findMemberDto() {
        try {
            this.start();
            return this.entityManager.createQuery(FIND_MEMBER_DTO_QUERY, MemberResponse.class)
                    .getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            this.end();
        }
    }

    public List<Member> findMembers(String jpql) {
        try {
            this.start();
            return this.entityManager.createQuery(jpql, Member.class)
                    .getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            this.end();
        }
    }

    public List<Member> findMembers(String jpql, int firstResult, int maxResult) {
        try {
            this.start();
            return this.entityManager.createQuery(jpql, Member.class)
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResult)
                    .getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            this.end();
        }
    }

}
