package com.zx.persistence.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductCommentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFavourCommentIsNull() {
            addCriterion("favour_comment is null");
            return (Criteria) this;
        }

        public Criteria andFavourCommentIsNotNull() {
            addCriterion("favour_comment is not null");
            return (Criteria) this;
        }

        public Criteria andFavourCommentEqualTo(Integer value) {
            addCriterion("favour_comment =", value, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentNotEqualTo(Integer value) {
            addCriterion("favour_comment <>", value, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentGreaterThan(Integer value) {
            addCriterion("favour_comment >", value, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentGreaterThanOrEqualTo(Integer value) {
            addCriterion("favour_comment >=", value, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentLessThan(Integer value) {
            addCriterion("favour_comment <", value, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentLessThanOrEqualTo(Integer value) {
            addCriterion("favour_comment <=", value, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentIn(List<Integer> values) {
            addCriterion("favour_comment in", values, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentNotIn(List<Integer> values) {
            addCriterion("favour_comment not in", values, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentBetween(Integer value1, Integer value2) {
            addCriterion("favour_comment between", value1, value2, "favourComment");
            return (Criteria) this;
        }

        public Criteria andFavourCommentNotBetween(Integer value1, Integer value2) {
            addCriterion("favour_comment not between", value1, value2, "favourComment");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andAnonymousIsNull() {
            addCriterion("anonymous is null");
            return (Criteria) this;
        }

        public Criteria andAnonymousIsNotNull() {
            addCriterion("anonymous is not null");
            return (Criteria) this;
        }

        public Criteria andAnonymousEqualTo(Integer value) {
            addCriterion("anonymous =", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotEqualTo(Integer value) {
            addCriterion("anonymous <>", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousGreaterThan(Integer value) {
            addCriterion("anonymous >", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousGreaterThanOrEqualTo(Integer value) {
            addCriterion("anonymous >=", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousLessThan(Integer value) {
            addCriterion("anonymous <", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousLessThanOrEqualTo(Integer value) {
            addCriterion("anonymous <=", value, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousIn(List<Integer> values) {
            addCriterion("anonymous in", values, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotIn(List<Integer> values) {
            addCriterion("anonymous not in", values, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousBetween(Integer value1, Integer value2) {
            addCriterion("anonymous between", value1, value2, "anonymous");
            return (Criteria) this;
        }

        public Criteria andAnonymousNotBetween(Integer value1, Integer value2) {
            addCriterion("anonymous not between", value1, value2, "anonymous");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNull() {
            addCriterion("comment_time is null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNotNull() {
            addCriterion("comment_time is not null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeEqualTo(Date value) {
            addCriterion("comment_time =", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotEqualTo(Date value) {
            addCriterion("comment_time <>", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThan(Date value) {
            addCriterion("comment_time >", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("comment_time >=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThan(Date value) {
            addCriterion("comment_time <", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThanOrEqualTo(Date value) {
            addCriterion("comment_time <=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIn(List<Date> values) {
            addCriterion("comment_time in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotIn(List<Date> values) {
            addCriterion("comment_time not in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeBetween(Date value1, Date value2) {
            addCriterion("comment_time between", value1, value2, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotBetween(Date value1, Date value2) {
            addCriterion("comment_time not between", value1, value2, "commentTime");
            return (Criteria) this;
        }

        public Criteria andUpvoteIsNull() {
            addCriterion("upvote is null");
            return (Criteria) this;
        }

        public Criteria andUpvoteIsNotNull() {
            addCriterion("upvote is not null");
            return (Criteria) this;
        }

        public Criteria andUpvoteEqualTo(Integer value) {
            addCriterion("upvote =", value, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteNotEqualTo(Integer value) {
            addCriterion("upvote <>", value, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteGreaterThan(Integer value) {
            addCriterion("upvote >", value, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteGreaterThanOrEqualTo(Integer value) {
            addCriterion("upvote >=", value, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteLessThan(Integer value) {
            addCriterion("upvote <", value, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteLessThanOrEqualTo(Integer value) {
            addCriterion("upvote <=", value, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteIn(List<Integer> values) {
            addCriterion("upvote in", values, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteNotIn(List<Integer> values) {
            addCriterion("upvote not in", values, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteBetween(Integer value1, Integer value2) {
            addCriterion("upvote between", value1, value2, "upvote");
            return (Criteria) this;
        }

        public Criteria andUpvoteNotBetween(Integer value1, Integer value2) {
            addCriterion("upvote not between", value1, value2, "upvote");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public void setSecondValue(Object secondValue) {
            this.secondValue = secondValue;
        }

        public void setNoValue(boolean noValue) {
            this.noValue = noValue;
        }

        public void setSingleValue(boolean singleValue) {
            this.singleValue = singleValue;
        }

        public void setBetweenValue(boolean betweenValue) {
            this.betweenValue = betweenValue;
        }

        public void setListValue(boolean listValue) {
            this.listValue = listValue;
        }

        public void setTypeHandler(String typeHandler) {
            this.typeHandler = typeHandler;
        }

        public Criterion() {
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}