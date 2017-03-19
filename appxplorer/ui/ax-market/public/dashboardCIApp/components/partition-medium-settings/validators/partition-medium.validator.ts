import { Host } from '@angular/core';
import { FormControl, ValidatorFn } from '@angular/forms';

export class PartitionMediumValidator {

    static validateReservedSpace(_availableSpace: number): ValidatorFn {

        return (control: FormControl): {[key: string]: boolean} => {
            
            let _reservedSpaceValue: number = control.value;
            if(_availableSpace < _reservedSpaceValue) {
                return { invalidSpace: true };
            }
        };
    }

}