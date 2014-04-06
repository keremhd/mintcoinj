/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.bitcoin.params;

import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Utils;
import org.spongycastle.util.encoders.Hex;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the testnet, a separate public instance of Mintcoin that has relaxed rules suitable for development
 * and testing of applications and new Mintcoin versions.
 */
public class MinttestNetParams extends NetworkParameters {
    public MinttestNetParams() {
        // TODO

        super();
        id = ID_TESTNET;
        // Genesis hash is 000000000933ea01ad0ee984209779baaec3ced90fa3f408719526f8d77f4943
        packetMagic = 0xcdf2c0efL;
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        proofOfWorkLimit  = Utils.decodeCompactBits(0x1e0fffffL);  // bnProofOfWorkLimitTestNet(~uint256(0) >> 20);
        proofOfStakeLimit = Utils.decodeCompactBits(0x1e0fffffL);  // bnProofOfStakeLimitTestNet(~uint256(0) >> 20);
        port = 22788;
        addressHeader = 111;
        p2shHeader = 196;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        dumpedPrivateKeyHeader = 239;
        genesisBlock.setTime(1296688602L);
        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        genesisBlock.setNonce(414098458);
        spendableCoinbaseDepth = 30;
        spendableStakebaseDepth = spendableCoinbaseDepth + 20;
        subsidyDecreaseBlockCount = 210000;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("000000000933ea01ad0ee984209779baaec3ced90fa3f408719526f8d77f4943"));
        alertSigningKey = Hex.decode("0471dc165db490094d35cde15b1f5d755fa6ad6f2b5ed0f340e3f17f57389c3c2af113a8cbcc885bde73305a553b5640c83021128008ddf882e856336269080496");


        dnsSeeds = new String[] {
                "testnet-seed.bitcoin.petertodd.org",
                "testnet-seed.bluematt.me"
        };
    }

    private static MinttestNetParams instance;
    public static synchronized MinttestNetParams get() {
        if (instance == null) {
            instance = new MinttestNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_TESTNET;
    }
}
