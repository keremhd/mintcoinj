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
import com.google.bitcoin.core.Sha256Hash;
import com.google.bitcoin.core.Transaction;
import com.google.bitcoin.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        proofOfWorkLimit  = Utils.decodeCompactBits(0x1e0fffffL);  // bnProofOfWorkLimit(~uint256(0) >> 20);
        proofOfStakeLimit = Utils.decodeCompactBits(0x1e0fffffL);  // bnProofOfStakeLimit(~uint256(0) >> 20);
        addressHeader = 51; // MintCoin: address begin with 'M'
        dumpedPrivateKeyHeader = 128 + addressHeader;
        p2shHeader = 8;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 12788;
        packetMagic = 0xced5dbfa;
        genesisBlock.setDifficultyTarget(0x1e0fffffL);
        genesisBlock.setTime(1391393693L);
        genesisBlock.setNonce(12488421);
        genesisBlock.getTransactions().get(0).setTime(1391393673L); // MintCoin main.cpp nChainStartTime
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 30;
        spendableStakebaseDepth = spendableCoinbaseDepth + 20;

        String merkleRoot = genesisBlock.getMerkleRoot().toString();
        checkState(merkleRoot.equals("cf174ca43b1b30e9a27f7fdc20ff9caf626499d023f1f033198fdbadf73ca747"),
                merkleRoot);

        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("af4ac34e7ef10a08fe2ba692eb9a9c08cf7e89fcf352f9ea6f0fd73ba3e5d03c"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        /*
        checkpoints.put(91722, new Sha256Hash("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        checkpoints.put(91812, new Sha256Hash("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        checkpoints.put(91842, new Sha256Hash("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        checkpoints.put(91880, new Sha256Hash("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        checkpoints.put(200000, new Sha256Hash("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));
         */


        dnsSeeds = new String[] {
/*
                "seed.bitcoin.sipa.be",        // Pieter Wuille
                "dnsseed.bluematt.me",         // Matt Corallo
                "dnsseed.bitcoin.dashjr.org",  // Luke Dashjr
                "seed.bitcoinstats.com",       // Chris Decker
*/
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
