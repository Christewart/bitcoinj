package org.bitcoinj.wallet;


import org.bitcoinj.core.Wallet;
import org.bitcoinj.testing.TestWithWallet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WalletFilesTest extends TestWithWallet {

    private final File walletFile = new File("test_wallet_file.wallet");
    private WalletFiles walletFiles;
    private final Long savingInterval = 1000L;
    private final TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        walletFiles = new WalletFiles(super.wallet,walletFile,savingInterval,timeUnit);
    }

    @After
    public void tearDown() throws Exception  {
        super.tearDown();
        walletFiles.shutdownAndWait();
        walletFile.delete();
    }


    @Test
    public void saveNowTest() throws IOException {
        walletFiles.saveNow();
        final Long lastSaveTime = walletFile.lastModified();
        final Long now = System.currentTimeMillis();
        final Long difference = now - lastSaveTime;
        assertTrue(difference > 0);
        assertTrue(difference <= savingInterval);
    }

    @Test





}
