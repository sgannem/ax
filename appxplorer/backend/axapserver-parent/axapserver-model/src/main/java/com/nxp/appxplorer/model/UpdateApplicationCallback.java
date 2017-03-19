package com.nxp.trustid.model;

import com.nxp.trustid.model.desfire.DesFireCard;

/**
 * Updates the application on a DESFire card.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface UpdateApplicationCallback {

  void perform(int aid, byte[] uid, DesFireCard desFireCard);
}
