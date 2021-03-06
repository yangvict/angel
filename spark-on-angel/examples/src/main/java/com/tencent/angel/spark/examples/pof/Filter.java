/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package com.tencent.angel.spark.examples.pof;

import com.tencent.angel.ml.matrix.psf.update.enhance.map.MapWithIndexFunc;
import io.netty.buffer.ByteBuf;

public class Filter implements MapWithIndexFunc {

  private int zeroIndex;

  public Filter(int zeroIndex) {
    this.zeroIndex = zeroIndex;
  }

  public Filter() {
  }

  @Override
  public double call(int index, double value) {
    double result = 0;
    if (index != zeroIndex) {
      result = value;
    }
    return result;
  }

  @Override
  public void serialize(ByteBuf buf) {
    buf.writeInt(zeroIndex);
  }

  @Override
  public void deserialize(ByteBuf buf) {
    zeroIndex = buf.readInt();
  }

  @Override
  public int bufferLen() {
    return 4;
  }

}
