package com.nxp.trustid.model.desfire;

/**
 * Wrapper for the ApduMagic library. (see {@code ApduMagicWrapper})
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface CardFactory {

  DesFireCard newDesFireCard(ApduHandler apduHandler);
}
