/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.yahoo.dataplatform.schema.parser;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ListObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.MapObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;

@Deprecated
public class OrcParserFactory{

  public static IParser get( final ObjectInspector objectInspector , final Object row ){

    switch( objectInspector.getCategory() ){
      case LIST:
        return new OrcListParser( row , (ListObjectInspector)objectInspector );
      case MAP:
        return new OrcMapParser( row , (MapObjectInspector)objectInspector );
      case STRUCT:
        return new OrcStructParser( row , (StructObjectInspector)objectInspector );
      case UNION:
      default:
        return new OrcNullParser();
    }
  }

  public static boolean hasParser( final ObjectInspector objectInspector ){
    switch( objectInspector.getCategory() ){
      case LIST:
      case MAP:
      case STRUCT:
        return true;
      case UNION:
      default:
        return false;
    }
  }

}
