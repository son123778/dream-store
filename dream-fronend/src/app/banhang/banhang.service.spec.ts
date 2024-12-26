import { TestBed } from '@angular/core/testing';

import { BanhangService } from './banhang.service';

describe('BanhangService', () => {
  let service: BanhangService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BanhangService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
