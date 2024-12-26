import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BanhangComponent } from './banhang.component';

describe('BanhangComponent', () => {
  let component: BanhangComponent;
  let fixture: ComponentFixture<BanhangComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BanhangComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BanhangComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
