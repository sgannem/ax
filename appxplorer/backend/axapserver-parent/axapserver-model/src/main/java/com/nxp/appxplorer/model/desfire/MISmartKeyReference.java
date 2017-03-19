package com.nxp.trustid.model.desfire;

import com.nxp.nfclib.interfaces.IKeyData;

/**
 * Holds the triplet keyNo, keyData and version.
 *
 * @author nxa30710
 */
public class MISmartKeyReference {

	private int keyNo;
	private IKeyData keyData;
	private byte keyVersion;
	private byte[] key;

	private MISmartKeyReference(final Builder builder) {
		super();
		keyNo = builder.keyNo;
		keyData = builder.keyData;
		keyVersion = builder.keyVersion;
		key = builder.key;
	}

	/**
	 * @return the keyNo
	 */
	public int getKeyNo() {
		return keyNo;
	}

	/**
	 * @return the keyData
	 */
	public IKeyData getKeyData() {
		return keyData;
	}

	/**
	 * @return the keyVersion
	 */
	public byte getKeyVersion() {
		return keyVersion;
	}
	
	/**
	 * @return the keyVersion
	 */
	public byte[] getKey() {
		return key;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MISmartKeyReference [keyNo=" + keyNo + ", keyData=" + keyData + ", keyVersion=" + keyVersion
				+ "]";
	}

	public static class Builder {

		private int keyNo;
		private IKeyData keyData;
		private byte keyVersion;
		private byte[] key;

		private Builder() {

		}

		/**
		 * @param keyNo
		 *            the keyNo to set
		 */
		public Builder setKeyNo(int keyNo) {
			this.keyNo = keyNo;
			return this;
		}

		/**
		 * @param keyData
		 *            the keyData to set
		 */
		public Builder setKeyData(IKeyData keyData) {
			this.keyData = keyData;
			return this;
		}

		/**
		 * @param keyVersion
		 *            the keyVersion to set
		 */
		public Builder setKeyVersion(byte keyVersion) {
			this.keyVersion = keyVersion;
			return this;
		}
		
		/**
		 * @param key
		 *            the key to set
		 */
		public Builder setKey(byte[] key) {
			this.key = key;
			return this;
		}
		
		public MISmartKeyReference build() {
			return new MISmartKeyReference(this);
		}

	}

}
