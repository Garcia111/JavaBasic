package jpa.test;

import jpa.AccountUserRepository;
import jpa.entity.AccountUserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
@DataJpaTest
public class JPAAndOrTest {

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Test
    public void andOrTest(){

        Specification<AccountUserEntity> specification =
                new Specification<AccountUserEntity>() {
                    @Override
                    public Predicate toPredicate(
                            Root<AccountUserEntity> root,
                            CriteriaQuery<?> query,
                            CriteriaBuilder criteriaBuilder) {
                        List<Predicate> listAnd = new ArrayList<Predicate>();
                        listAnd.add(
                                criteriaBuilder.like(
                                        root.get("accountName").as(String.class),
                                        "张"));
                        Predicate[] array_and = new Predicate[listAnd.size()];
                        Predicate Pre_And = criteriaBuilder.and(listAnd.toArray(array_and));

                        List<Predicate> listOr = new ArrayList<Predicate>();
                        listOr.add(
                                criteriaBuilder.like(
                                        root.get("mdn").as(String.class), "123"));
                        listOr.add(
                                criteriaBuilder.like(
                                        root.get("name").as(String.class), "张"));
                        Predicate[] arrayOr = new Predicate[listOr.size()];
                        Predicate Pre_Or = criteriaBuilder.or(listOr.toArray(arrayOr));
                        Order order = criteriaBuilder.asc(root.get("createdTime"));
                        return query.orderBy(order).where(Pre_And, Pre_Or).getRestriction();
                    }

                    private static final long serialVersionUID = 6227700451875212498L;
                };

        List<AccountUserEntity> list = accountUserRepository.findAll(specification);
    }
}
