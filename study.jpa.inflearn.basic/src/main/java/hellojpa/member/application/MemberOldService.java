package hellojpa.member.application;

import hellojpa.member.domain.MemberOld;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class MemberOldService {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public MemberOldService() {
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

    public Optional<MemberOld> createMember(String name) {
        try {
            MemberOld memberOld = new MemberOld();
            memberOld.setName(name);

            this.entityManager.persist(memberOld);
            this.transaction.commit();
            return Optional.of(memberOld);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public Optional<MemberOld> findMember(Long id) {
        this.transaction.begin();
        try {
            MemberOld memberOld = this.entityManager.find(MemberOld.class, id);
            this.transaction.commit();
            return Optional.of(memberOld);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public Optional<List<MemberOld>> findMemberByJPQL(Long id) {
        this.transaction.begin();
        try {
            List<MemberOld> memberOlds = this.entityManager
                    .createQuery("select m from Member m", MemberOld.class)
//                    .setFirstResult(5)    // 이런식으로 페이징도 쉽게 가능
//                    .setMaxResults(10)
                    .getResultList();

            this.transaction.commit();
            return Optional.of(memberOlds);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public Optional<MemberOld> updateMember(MemberOld memberOld) {
        this.transaction.begin();
        try {
            this.entityManager.persist(memberOld);
            this.transaction.commit();
            return Optional.of(memberOld);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        MemberOldService jpaApplication = new MemberOldService();
        jpaApplication.start();
        MemberOld memberOld = jpaApplication.createMember("test2").get();
        MemberOld foundMemberOld = jpaApplication.findMember(memberOld.getId()).get();
        foundMemberOld.setName("change name");
        MemberOld updatedMemberOld = jpaApplication.updateMember(foundMemberOld).get();

        System.out.println("수정 된 유저 : " + updatedMemberOld);
        jpaApplication.end();
    }
}
