package org.example.directives.utils;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;

public class FormatPreservingUtil {

    public static byte[] key = new byte[]{
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01,
            (byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x02,
            (byte) 0x03, (byte) 0x03, (byte) 0x03, (byte) 0x03
    };

    public static byte[] tweak = new byte[] {(byte) 0xEf5, (byte) 0x03, (byte) 0xF9};

    public static FormatPreservingEncryption defaultFormatPreservingEncryption() {
        return FormatPreservingEncryptionBuilder.ff1Implementation()
                .withDefaultDomain()
                .withDefaultPseudoRandomFunction(key)
                .withDefaultLengthRange()
                .build();
    }
}
