import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BanhangonlineComponent } from './banhangonline.component';

describe('BanhangonlineComponent', () => {
  let component: BanhangonlineComponent;
  let fixture: ComponentFixture<BanhangonlineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BanhangonlineComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BanhangonlineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
