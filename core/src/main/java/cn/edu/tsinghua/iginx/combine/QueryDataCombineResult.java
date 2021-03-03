/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.edu.tsinghua.iginx.combine;

import cn.edu.tsinghua.iginx.thrift.QueryDataResp;
import cn.edu.tsinghua.iginx.thrift.QueryDataSet;
import cn.edu.tsinghua.iginx.thrift.Status;

import java.util.List;

public class QueryDataCombineResult extends DataCombineResult {

    private final QueryDataSet queryDataSet;

    private final List<String> paths;

    public QueryDataCombineResult(Status status, QueryDataSet queryDataSet, List<String> paths) {
        super(status);
        this.queryDataSet = queryDataSet;
        this.paths = paths;
    }

    public QueryDataResp generateResp() {
        QueryDataResp resp = new QueryDataResp(getStatus());
        resp.setPaths(paths);
        resp.setQueryDataSet(queryDataSet);
        return resp;
    }

}