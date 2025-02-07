package cn.edu.tsinghua.iginx.sql.statement.frompart;

import cn.edu.tsinghua.iginx.engine.shared.Constants;
import cn.edu.tsinghua.iginx.sql.statement.frompart.join.JoinCondition;
import cn.edu.tsinghua.iginx.sql.statement.selectstatement.BinarySelectStatement;
import cn.edu.tsinghua.iginx.sql.statement.selectstatement.SelectStatement;
import cn.edu.tsinghua.iginx.sql.statement.selectstatement.UnarySelectStatement;
import java.util.List;
import java.util.Map;

public class SubQueryFromPart implements FromPart {

  private final SelectStatement subQuery;
  private final List<String> patterns;
  private JoinCondition joinCondition;
  private final String alias;

  public SubQueryFromPart(SelectStatement subQuery) {
    this(subQuery, "");
  }

  public SubQueryFromPart(SelectStatement subQuery, String alias) {
    this.subQuery = subQuery;
    this.patterns = subQuery.calculatePrefixSet();
    this.alias = alias;
  }

  public SubQueryFromPart(SelectStatement subQuery, JoinCondition joinCondition) {
    this(subQuery, joinCondition, "");
  }

  public SubQueryFromPart(SelectStatement subQuery, JoinCondition joinCondition, String alias) {
    this.subQuery = subQuery;
    this.patterns = subQuery.calculatePrefixSet();
    this.joinCondition = joinCondition;
    this.alias = alias;
  }

  @Override
  public FromPartType getType() {
    return FromPartType.SubQuery;
  }

  @Override
  public Map<String, String> getAliasMap() {
    return subQuery.getSubQueryAliasMap(alias);
  }

  @Override
  public boolean hasAlias() {
    return alias != null && !alias.isEmpty();
  }

  @Override
  public boolean hasSinglePrefix() {
    if (hasAlias()) {
      return true;
    }

    SelectStatement s = subQuery;
    while (s.getSelectType().equals(SelectStatement.SelectStatementType.BINARY)) {
      s = ((BinarySelectStatement) s).getLeftQuery();
    }

    if (((UnarySelectStatement) s).getFromParts().size() > 1) {
      return false;
    }
    return ((UnarySelectStatement) s).getFromPart(0).hasSinglePrefix();
  }

  @Override
  public List<String> getPatterns() {
    return patterns;
  }

  @Override
  public String getOriginPrefix() {
    return "";
  }

  @Override
  public String getPrefix() {
    if (hasAlias()) {
      return alias;
    }
    // 如果子查询没有一个公共的前缀，返回null
    if (hasSinglePrefix()) {
      for (String pattern : patterns) {
        if (pattern.endsWith(Constants.ALL_PATH_SUFFIX)) {
          return pattern.substring(0, pattern.length() - 2);
        }
      }
    }
    return null;
  }

  @Override
  public JoinCondition getJoinCondition() {
    return joinCondition;
  }

  @Override
  public void setJoinCondition(JoinCondition joinCondition) {
    this.joinCondition = joinCondition;
  }

  public SelectStatement getSubQuery() {
    return subQuery;
  }

  @Override
  public List<String> getFreeVariables() {
    return subQuery.getFreeVariables();
  }
}
