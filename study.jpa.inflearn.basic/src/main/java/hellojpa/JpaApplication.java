package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class JpaApplication {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public JpaApplication() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("hello");
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

    public Optional<Member> createMember(long id, String name) {
        try {
            Member member = new Member();
            member.setId(id);
            member.setName(name);

            this.entityManager.persist(member);
            this.transaction.commit();
            return Optional.of(member);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public Optional<Member> findMember(Long id) {
        this.transaction.begin();
        try {
            Member member = this.entityManager.find(Member.class, id);
            this.transaction.commit();
            return Optional.of(member);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public Optional<List<Member>> findMemberByJPQL(Long id) {
        this.transaction.begin();
        try {
            List<Member> members = this.entityManager
                    .createQuery("select m from Member m", Member.class)
//                    .setFirstResult(5)    // 이런식으로 페이징도 쉽게 가능
//                    .setMaxResults(10)
                    .getResultList();

            this.transaction.commit();
            return Optional.of(members);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public Optional<Member> updateMember(Member member) {
        this.transaction.begin();
        try {
            this.entityManager.persist(member);
            this.transaction.commit();
            return Optional.of(member);
        } catch (Exception e) {
            this.transaction.rollback();
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        JpaApplication jpaApplication = new JpaApplication();
        jpaApplication.start();
        Member member = jpaApplication.createMember(3L, "test2").get();
        Member foundMember = jpaApplication.findMember(member.getId()).get();
        foundMember.setName("change name");
        Member updatedMember = jpaApplication.updateMember(foundMember).get();

        System.out.println("수정 된 유저 : " + updatedMember);
        jpaApplication.end();
    }
}
