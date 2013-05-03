require 'spec_helper'

describe Guest do
  it { should have_db_column(:name).of_type(:string) }
  it { should have_db_column(:forename).of_type(:string) }
  it { should have_db_column(:email).of_type(:string) }
end
