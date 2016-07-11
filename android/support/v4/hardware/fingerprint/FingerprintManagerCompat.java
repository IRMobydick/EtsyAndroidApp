package android.support.v4.hardware.fingerprint;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.AuthenticationResultInternal;
import android.support.v4.os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

public final class FingerprintManagerCompat {
    static final FingerprintManagerCompatImpl IMPL;
    private Context mContext;

    interface FingerprintManagerCompatImpl {
        void authenticate(Context context, CryptoObject cryptoObject, int i, CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler);

        boolean hasEnrolledFingerprints(Context context);

        boolean isHardwareDetected(Context context);
    }

    class Api23FingerprintManagerCompatImpl implements FingerprintManagerCompatImpl {

        /* renamed from: android.support.v4.hardware.fingerprint.FingerprintManagerCompat.Api23FingerprintManagerCompatImpl.1 */
        final class C00891 extends android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.AuthenticationCallback {
            final /* synthetic */ AuthenticationCallback val$callback;

            C00891(AuthenticationCallback authenticationCallback) {
                this.val$callback = authenticationCallback;
            }

            public void onAuthenticationError(int i, CharSequence charSequence) {
                this.val$callback.onAuthenticationError(i, charSequence);
            }

            public void onAuthenticationHelp(int i, CharSequence charSequence) {
                this.val$callback.onAuthenticationHelp(i, charSequence);
            }

            public void onAuthenticationSucceeded(AuthenticationResultInternal authenticationResultInternal) {
                this.val$callback.onAuthenticationSucceeded(new AuthenticationResult(Api23FingerprintManagerCompatImpl.unwrapCryptoObject(authenticationResultInternal.getCryptoObject())));
            }

            public void onAuthenticationFailed() {
                this.val$callback.onAuthenticationFailed();
            }
        }

        public boolean hasEnrolledFingerprints(Context context) {
            return FingerprintManagerCompatApi23.hasEnrolledFingerprints(context);
        }

        public boolean isHardwareDetected(Context context) {
            return FingerprintManagerCompatApi23.isHardwareDetected(context);
        }

        public void authenticate(Context context, CryptoObject cryptoObject, int i, CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler) {
            FingerprintManagerCompatApi23.authenticate(context, wrapCryptoObject(cryptoObject), i, cancellationSignal != null ? cancellationSignal.getCancellationSignalObject() : null, wrapCallback(authenticationCallback), handler);
        }

        private static android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                return new android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.CryptoObject(cryptoObject.getMac());
            }
            return null;
        }

        private static CryptoObject unwrapCryptoObject(android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.CryptoObject cryptoObject) {
            if (cryptoObject == null) {
                return null;
            }
            if (cryptoObject.getCipher() != null) {
                return new CryptoObject(cryptoObject.getCipher());
            }
            if (cryptoObject.getSignature() != null) {
                return new CryptoObject(cryptoObject.getSignature());
            }
            if (cryptoObject.getMac() != null) {
                return new CryptoObject(cryptoObject.getMac());
            }
            return null;
        }

        private static android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23.AuthenticationCallback wrapCallback(AuthenticationCallback authenticationCallback) {
            return new C00891(authenticationCallback);
        }
    }

    public abstract class AuthenticationCallback {
        public void onAuthenticationError(int i, CharSequence charSequence) {
        }

        public void onAuthenticationHelp(int i, CharSequence charSequence) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        }

        public void onAuthenticationFailed() {
        }
    }

    public final class AuthenticationResult {
        private CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject cryptoObject) {
            this.mCryptoObject = cryptoObject;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    public class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(Signature signature) {
            this.mSignature = signature;
            this.mCipher = null;
            this.mMac = null;
        }

        public CryptoObject(Cipher cipher) {
            this.mCipher = cipher;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(Mac mac) {
            this.mMac = mac;
            this.mCipher = null;
            this.mSignature = null;
        }

        public Signature getSignature() {
            return this.mSignature;
        }

        public Cipher getCipher() {
            return this.mCipher;
        }

        public Mac getMac() {
            return this.mMac;
        }
    }

    class LegacyFingerprintManagerCompatImpl implements FingerprintManagerCompatImpl {
        public boolean hasEnrolledFingerprints(Context context) {
            return false;
        }

        public boolean isHardwareDetected(Context context) {
            return false;
        }

        public void authenticate(Context context, CryptoObject cryptoObject, int i, CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler) {
        }
    }

    public static FingerprintManagerCompat from(Context context) {
        return new FingerprintManagerCompat(context);
    }

    private FingerprintManagerCompat(Context context) {
        this.mContext = context;
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            IMPL = new Api23FingerprintManagerCompatImpl();
        } else {
            IMPL = new LegacyFingerprintManagerCompatImpl();
        }
    }

    public boolean hasEnrolledFingerprints() {
        return IMPL.hasEnrolledFingerprints(this.mContext);
    }

    public boolean isHardwareDetected() {
        return IMPL.isHardwareDetected(this.mContext);
    }

    public void authenticate(@Nullable CryptoObject cryptoObject, int i, @Nullable CancellationSignal cancellationSignal, @NonNull AuthenticationCallback authenticationCallback, @Nullable Handler handler) {
        IMPL.authenticate(this.mContext, cryptoObject, i, cancellationSignal, authenticationCallback, handler);
    }
}