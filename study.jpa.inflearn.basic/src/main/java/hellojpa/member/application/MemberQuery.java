package hellojpa.member.application;

public class MemberQuery {

    public static final String FIND_MEMBER_ORDER_BY_NAME
            = "select m"
            + " from Member m"
            + " order by m.name desc";

    public static final String FIND_MEMBER_DTO_QUERY
            = "select new hellojpa.member.dto.MemberResponse(m.id, m.name)"
            + " from Member m";

    public static final String FIND_MEMBER_INNER_JOIN_ORDER
            = "select m, o"
            + " from Member m"
            + " inner join m.order o";
//            == " join m.order o";

    public static final String FIND_MEMBER_OUTER_JOIN_ORDER
            = "select m, o"
            + " from Member m"
            + " left join m.order o";
//            == " left outer join m.order o";
//          + " on m.id < 100"

    public static final String FIND_MEMBER_THETA_JOIN_ORDER
            = "select m, o"
            + " from Member m, Order o"
            + " where m.id = o.id";
}
