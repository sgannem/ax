package com.nxp.trustid.communication.protobuf.converter;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
class EnumMapper {

  private EnumMapper() {
    // empty
  }

  static <T extends Enum<T>> T mapEnum(final Enum<?> sourceEnumValue, final Class<T> targetEnumClass) {
    assert (sourceEnumValue.getDeclaringClass() != targetEnumClass);
    return Enum.valueOf(targetEnumClass, sourceEnumValue.name());
  }
}
