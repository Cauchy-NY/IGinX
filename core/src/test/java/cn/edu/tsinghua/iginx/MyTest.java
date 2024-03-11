package cn.edu.tsinghua.iginx;

import cn.edu.tsinghua.iginx.engine.shared.operator.Operator;
import cn.edu.tsinghua.iginx.optimizer.TreeBuilder;
import cn.edu.tsinghua.iginx.utils.FastjsonSerializeUtils;

public class MyTest {

  @org.junit.Test
  public void testA() {
    Operator before = TreeBuilder.buildFilterFragmentTree();
    String a = FastjsonSerializeUtils.serialize(before);
    Operator after = FastjsonSerializeUtils.deserialize(a, Operator.class);
  }
}
