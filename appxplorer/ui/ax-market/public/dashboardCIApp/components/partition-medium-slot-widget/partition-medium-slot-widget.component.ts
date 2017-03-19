import { Component, Input, OnInit, Host } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { Select2OptionData } from 'ng2-select2/ng2-select2';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { PartitionMediumSettingsComponent } from '../partition-medium-settings/partition-medium-settings.component';

@Component({
    moduleId: module.id,
    selector: 'partition-medium-slot-widget',
    templateUrl: 'partition-medium-slot-widget.component.html',
    styleUrls: ['partition-medium-slot-widget.component.scss']
})

export class PartitionMediumSlotWidgetComponent {

    $:JQueryStatic;
    @Input('slot-type') slotType: string;
    expandWidget: boolean;
    slotSizeList: Array<Select2OptionData> = [];
    private _master: PartitionMediumSettingsComponent;
    _isValidPartitionComponent: boolean = true;
    _availableSpace: number;
    _reservedSpace: number;
    _sharedSpace: number;
    _dedicatedSpace: number;

    slotSpaceForm : FormGroup;

    slotCount: number;
    currentSlotValue: number;
    slotValueChangedByUser: boolean;
    
    constructor(private _fb: FormBuilder, @Host() _master: PartitionMediumSettingsComponent) {
        this.expandWidget = false;
        this._master = _master;
        this._availableSpace = 0;
        this._sharedSpace = 0;
        this._dedicatedSpace = 0;
        this._reservedSpace = 0;
    }

    ngOnInit() {
        
        this.slotCount = 1;
        this.currentSlotValue = 1;
        this.slotValueChangedByUser = false;

        if(this.slotType=='shared') {
            var sharedSlotSize: number = this.getSharedSlotListSize();
            for(var i=1;i<=sharedSlotSize;i++) {
                this.slotSizeList.push({id: ''+i, text: ''+i});
            }
        } else if(this.slotType=='dedicated') {
            var dedicatedSlotSize: number = this.getDedicatedSlotListSize();
            for(var i=1;i<=dedicatedSlotSize;i++) {
                this.slotSizeList.push({id: ''+i, text: ''+i});
            }
        }

        this.slotSpaceForm = this._fb.group({
            slotCount: new FormControl(''+this.slotCount, [Validators.required]),
            slotSizeEach: new FormControl('', [Validators.required])
        });

        this.syncDownSlotData();

        setTimeout("$('.select2-selection--single').css('background-color', 'rgba(221, 221, 221) !important', 'important')", 100);
        setTimeout("$(document.body).on('change','.select2-hidden-accessible',function(){})", 500);

        let timer = Observable.timer(1000, 1000);
        timer.subscribe(t=> {
            this.syncDownReservedSpace();

            let _slotCount: number = this.slotCount;
            let _slotSizeEach: number = this.slotSpaceForm.get('slotSizeEach').value;
            if(_slotSizeEach==undefined || _slotSizeEach<=0) {
                _slotSizeEach = 0;
            }

            if(this.slotType=='shared' && _slotCount!=this.getSharedSlotCount() && _slotCount>0 && this.getSharedSlotCount()>0) {
                this.slotCount = this.getSharedSlotCount();
                if(this.slotCount!=this.currentSlotValue && (!this.slotValueChangedByUser)) {
                    this.currentSlotValue = this.slotCount;
                }
            } else if(this.slotType=='dedicated' && this.slotCount!=this.getDedicatedSlotCount() && _slotCount>0 && this.getDedicatedSlotCount()>0) {
                this.slotCount = this.getDedicatedSlotCount();
                if(this.slotCount!=this.currentSlotValue && (!this.slotValueChangedByUser)) {
                    this.currentSlotValue = this.slotCount;
                }
            }

            if(this.slotType=='shared' && this.getSharedWidgetClosed()) {
                this.slotCount = 1;
                this.expandWidget = false;
                this.setSharedWidgetClosed(false);
                this.slotSpaceForm.controls['slotSizeEach'].setValue('', {onlySelf: true});
            } else if(this.slotType=='dedicated' && this.getDedicatedWidgetClosed()) {
                this.slotCount = 1;
                this.expandWidget = false;
                this.setDedicatedWidgetClosed(false);
                this.slotSpaceForm.controls['slotSizeEach'].setValue('', {onlySelf: true});
            }

            if(this.currentSlotValue<=0) {
                this.currentSlotValue = 1;
                this.slotCount = 1;
                this.slotSpaceForm.controls['slotSizeEach'].setValue('', {onlySelf: true});
            }
        });

    }

    private syncDownReservedSpace() {

        if(this.getReservedSpace()<=0 && this.slotType=='shared') {
            this.setSharedSlotCount(0);
            this.setSharedSlotSizeEach(0);
            this.slotSpaceForm.controls['slotSizeEach'].setValue(0, {onlySelf: true});
            this.expandWidget = false;
        } else if(this.getReservedSpace()<=0 && this.slotType=='dedicated') {
            this.setDedicatedSlotCount(0);
            this.setDedicatedSlotSizeEach(0);
            this.slotSpaceForm.controls['slotSizeEach'].setValue(0, {onlySelf: true});
            this.expandWidget = false;
        }

        this._reservedSpace = this.getReservedSpace();
        this._sharedSpace = this.getSharedSlotCount() * this.getSharedSlotSizeEach();
        this._dedicatedSpace = this.getDedicatedSlotCount() * this.getDedicatedSlotSizeEach();
        this._availableSpace = this.getCardSize() - this._reservedSpace - this._sharedSpace - this._dedicatedSpace;
    }

    private syncDownSlotData() {
        
        if(this.slotType=='shared' && this.getSharedSlotSizeEach()>0) {
            this.currentSlotValue = this.getSharedSlotCount();
            this.slotSpaceForm.controls['slotSizeEach'].setValue(this.getSharedSlotSizeEach(), {onlySelf: true});
            this.expandWidget = true;
        } else if(this.slotType=='dedicated' && this.getDedicatedSlotSizeEach()>0) {
            this.currentSlotValue = this.getDedicatedSlotCount();
            this.slotSpaceForm.controls['slotSizeEach'].setValue(this.getDedicatedSlotSizeEach(), {onlySelf: true});
            this.expandWidget = true;
        }
    }

    public updateSlotCount(e: any): void {
        this.slotCount = e.value;
        this.currentSlotValue = e.value;
    }

    public updateSlotAction(e: any): void {
        this.slotValueChangedByUser = true;
    }

    public addSlotSpace(e: any): void {

        let _slotCount: number = this.currentSlotValue;
        let _slotSizeEach: number = this.slotSpaceForm.get('slotSizeEach').value;
        if(_slotSizeEach==undefined || _slotSizeEach<=0) {
            _slotSizeEach = 0;
        }

        if(this.slotType=='shared') {

            let _isValidPartition: boolean  = this.isValidPartition(this.getCardSize(), this.getReservedSpace(), this.slotCount, _slotSizeEach, this.getDedicatedSlotCount(), this.getDedicatedSlotSizeEach());
            this._isValidPartitionComponent = _isValidPartition;

            if((this.getReservedSpace() < this.getCardSize()) && _isValidPartition) {
                this.setSharedSlotCount(_slotCount);
                this.setSharedSlotSizeEach(_slotSizeEach);
            }

            if(_slotSizeEach==0) {
                this.setSharedSlotCount(0);
                this.setSharedSlotSizeEach(0);
            }
            this.slotValueChangedByUser = false;
        } else if(this.slotType=='dedicated') {

            let _isValidPartition: boolean  = this.isValidPartition(this.getCardSize(), this.getReservedSpace(), this.getSharedSlotCount(), this.getSharedSlotSizeEach(), _slotCount, _slotSizeEach);
            this._isValidPartitionComponent = _isValidPartition;

            if((this.getReservedSpace() < this.getCardSize())) {
                this.setDedicatedSlotCount(_slotCount);
                this.setDedicatedSlotSizeEach(_slotSizeEach);
            }

            if(_slotSizeEach==0) {
                this.setDedicatedSlotCount(0);
                this.setDedicatedSlotSizeEach(0);
            }
            this.slotValueChangedByUser = false;
        }
    }

    private isValidPartition(cardSize:number, reservedSpace:number, sharedSlotCount:number, sharedSlotSizeEach:number, dedicatedSlotCount: number, dedicatedSlotSizeEach: number) {

        let _availableCardSpace: number = cardSize - reservedSpace;
        let _isValidPartition: boolean  = true;

        if(((sharedSlotCount * sharedSlotSizeEach) + (dedicatedSlotCount * dedicatedSlotSizeEach)) > _availableCardSpace) {
            _isValidPartition = false;
        }

        return _isValidPartition;
    }
    
    /* Model functions start */
    public getCardSize() {
        return this._master.getCardSize();
    }

    public getReservedSpace() {
        return this._master.getReservedSpace();
    }

    public setReservedSpace(reservedSpace: number) {
        return this._master.setReservedSpace(reservedSpace);
    }

    public getSharedSlotCount() {
        return this._master.getSharedSlotCount();
    }

    public setSharedSlotCount(sharedSlotCount: number) {
        this._master.setSharedSlotCount(sharedSlotCount);
    }

    public getSharedSlotSizeEach() {
        return this._master.getSharedSlotSizeEach();
    }

    public setSharedSlotSizeEach(sharedSlotSizeEach: number) {
        this._master.setSharedSlotSizeEach(sharedSlotSizeEach);
    }

    public getDedicatedSlotCount() {
        return this._master.getDedicatedSlotCount();
    }

    public setDedicatedSlotCount(dedicatedSlotCount: number) {
        this._master.setDedicatedSlotCount(dedicatedSlotCount);
    }

    public getDedicatedSlotSizeEach() {
        return this._master.getDedicatedSlotSizeEach();
    }

    public setDedicatedSlotSizeEach(dedicatedSlotSizeEach: number) {
        this._master.setDedicatedSlotSizeEach(dedicatedSlotSizeEach);
    }
    
    public getSharedSlotListSize() {
        return this._master.getSharedSlotListSize();
    }
    
    public getDedicatedSlotListSize() {
        return this._master.getDedicatedSlotListSize();
    }
    
    public getSharedWidgetClosed() {
        return this._master.getSharedWidgetClosed();
    }
    
    public setSharedWidgetClosed(sharedSlotCloseWidget: boolean) {
        this._master.setSharedWidgetClosed(sharedSlotCloseWidget);
    }
    
    public getDedicatedWidgetClosed() {
        return this._master.getDedicatedWidgetClosed();
    }
    
    public setDedicatedWidgetClosed(dedicatedSlotCloseWidget: boolean) {
        this._master.setDedicatedWidgetClosed(dedicatedSlotCloseWidget);
    }
    /* Model functions start */

}
