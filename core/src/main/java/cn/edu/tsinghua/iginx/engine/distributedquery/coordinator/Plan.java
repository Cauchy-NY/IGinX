package cn.edu.tsinghua.iginx.engine.distributedquery.coordinator;

import cn.edu.tsinghua.iginx.engine.shared.operator.Operator;
import java.util.Map;

public class Plan {

  private final Operator root;

  private final Map<Integer, Operator> subPlans;

  public Plan(Operator root, Map<Integer, Operator> subPlans) {
    this.root = root;
    this.subPlans = subPlans;
  }

  public Operator getRoot() {
    return root;
  }

  private Operator getSubPlan(int id) {
    return subPlans.get(id);
  }
}
