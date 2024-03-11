package cn.edu.tsinghua.iginx.engine.shared.operator;

import cn.edu.tsinghua.iginx.engine.shared.operator.type.OperatorType;
import cn.edu.tsinghua.iginx.engine.shared.source.Source;

public class Load extends AbstractUnaryOperator {

  private final Operator operator;

  public Load(Source source, Operator operator) {
    super(OperatorType.Load, source);
    this.operator = operator;
  }

  public Operator getOperator() {
    return operator;
  }

  @Override
  public Operator copy() {
    return new Load(getSource().copy(), operator.copy());
  }

  @Override
  public String getInfo() {
    return getSource().toString();
  }

  @Override
  public UnaryOperator copyWithSource(Source source) {
    return new Load(source, operator.copy());
  }
}
